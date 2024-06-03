// import './styles/SettingsPage_Style.css';
import { useState } from 'react';
import InsideNavBar from './InsideNavBar';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import successIcon from '../assets/checkmark.svg'
import errorIcon from '../assets/cross-circle.svg'

function SettingsPage(){

    const navigate = useNavigate(); 

    const handleClick = () => {
        navigate('/project');
      };

      const [userMessage,setUserMessage] = useState('')
      const [userMessageIcon,setUserMessageIcon] = useState(null)
      const [isError,setIsError] = useState(false)
      const [showPassword, setShowPassword] = useState({
        currentPassword: false,
        newPassword: false,
        confirmNewPassword: false,
      });

      const [formData, setFormData] = useState({
        currentPassword: '',
        newPassword: '',
        confirmNewPassword: '',
      });
    
      const [agreeTerms, setAgreeTerms] = useState(false);
    
      const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
          ...formData,
          [name]: value,
        });
      };
    
      const handleAgreeTermsChange = (e) => {
        setAgreeTerms(e.target.checked);
      };
    
      const handleSubmit = async (e) => {
        e.preventDefault();
        const { currentPassword, newPassword, confirmNewPassword } = formData;
    
  
        if (!agreeTerms) {
          setUserMessage('You must agree to the terms and conditions')
          setUserMessageIcon(errorIcon)
          setIsError(true)
          return;
        }
    
        try {
          const response = await axios.post('http://localhost:8080/change/password',formData, {
            withCredentials:true
          });
            console.log(response.data)
            setUserMessage(response.data.message);
            setUserMessageIcon(successIcon);
            setIsError(false);
            setAgreeTerms(false);
            setFormData({
                currentPassword: '',
                newPassword: '',
                confirmNewPassword: '',
            })
    
        } catch (error) {

          if (error.response) {
            
            setUserMessage(error.response.data.message||error.response.data.error)
            setUserMessageIcon(errorIcon)
            console.log(error.response.data)
            setIsError(true)
          }else{
            setUserMessage('error occured!')
            setUserMessageIcon(errorIcon)
            setIsError(true)
          }
        }
      };

      const handleReset = () => {
        setFormData({
          currentPassword: '',
          newPassword: '',
          confirmNewPassword: '',
        });

        setUserMessage('')
        setUserMessageIcon(null)
        setIsError(true)
        setAgreeTerms(false);
      };
    
      const handleCancel = () => {
        handleReset()
      };

      const togglePasswordVisibility = (field) => {
        setShowPassword((prevState) => ({
          ...prevState,
          [field]: !prevState[field],
        }));
      };



    return(
        <div className="page-row">
        <Sidebar />
        <div className="project-wrapper">
          <Navbar />
        <div className="Edit_Container settings_container">
            <div className="Nav_Holder">
            <InsideNavBar />
            </div>
            <div className="Edit_Elements_Container_Holder settings_sections">
            <div className="Edit_Elements_Container">
            <div className='settings_header'>
              <h1>Edit Profile</h1>
              <p> Manage your Name, and Email Addresses</p>
              <p> Below are the name and email addresses on file for your account.</p>
            </div>
            
            <form className="setting-form" onSubmit={handleSubmit}>
                    <div className="settings_table_section">
                    <div className="Edit_Table_section_name">
                    <p>Enter Old password</p>
                    </div>
                    <div className="Edit_Table_section_name">
                    <input
                        type={showPassword.currentPassword ? 'text' : 'password'}
                        name="currentPassword"
                        placeholder="Enter old password"
                        value={formData.currentPassword}
                        onChange={handleInputChange}
                    />
                    <i className="lni lni-eye showPassword"  onClick={() => togglePasswordVisibility('currentPassword')}></i>
                    </div>
                    </div>
                    <div className="settings_table_section">
                    <div className="Edit_Table_section_name">
                        <p>Enter New Password</p>
                    </div>
                    <div className="Edit_Table_section_name">
                        <input
                         type={showPassword.newPassword ? 'text' : 'password'}
                        name="newPassword"
                        placeholder="Enter new password"
                        value={formData.newPassword}
                        onChange={handleInputChange}
                    />
                    <i className="lni lni-eye showPassword"  onClick={() => togglePasswordVisibility('newPassword')}></i>
                    </div>
                    </div>

                    <div className="settings_table_section">
                    <div className="Edit_Table_section_name">
                        <p>Confirm New Password</p>
                    </div>
                    <div className="Edit_Table_section_name">
                        <input
                         type={showPassword.confirmNewPassword ? 'text' : 'password'}
                        name="confirmNewPassword"
                        placeholder="Confirm new password"
                        value={formData.confirmNewPassword}
                        onChange={handleInputChange}
                    />
                   <i className="lni lni-eye showPassword" onClick={() => togglePasswordVisibility('confirmNewPassword')}/>
                    </div>
                    </div>

                    <div className="Edit_Table_section_Button_Sction password_condition">
                      <input type="checkbox" checked={agreeTerms}onChange={handleAgreeTermsChange}/>
                      <p>
                          I agree with the Terms and <b>Conditions.</b>
                      </p>
                    </div>
                    <div className="Edit_Table_section_Button_Section  settings-buttons">
                      <button type="button" onClick={handleCancel}>Cancel</button>
                      <button type="submit">Save Changes</button>
                    </div>
                 
            </form>
            
            <div className="userMessage-wrapper">
                <img src={userMessageIcon} />
                <h2 className={`userMessage ${isError ? 'error' : 'success'}`}>{userMessage}</h2>
            </div>
            
            </div>
            </div>
            </div>
            
            </div>
        </div>

    )
}
export default SettingsPage;