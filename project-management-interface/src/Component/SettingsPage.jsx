// import './styles/SettingsPage_Style.css';
import InsideNavBar from './InsideNavBar';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import { useNavigate } from 'react-router-dom';

function SettingsPage(){

    const navigate = useNavigate(); 

    const handleClick = () => {
        navigate('/project');
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
                <div className='settings_header'>
                        <h1><i class="lni lni-write"></i> Edit Profile</h1>
                        <p> Manage your Name, and Email Addresses</p>
                        <p> Below are the name and email addresses on file for your account.</p>
                    </div>
                    <div className="table_section">
                        <div className="Edit_Table_section_name">
                            <p>Enter old password</p>
                        </div>
                        <div className="Edit_Table_section_name">
                            <input type="text" placeholder="Enter old password"/>
                            {/* <RiLockPasswordLine /> */}
                        </div>
                    </div>
                    <div className="table_section">
                        <div className="Edit_Table_section_name">
                            <p>Enter your password</p>
                        </div>
                        <div className="Edit_Table_section_name">
                            <input type="text" placeholder="Enter new password"/>
                            {/* <RiLockPasswordLine /> */}
                        </div>
                    </div>
                    <div className="table_section">
                        <div className="Edit_Table_section_name">
                            <p>Confirm Password</p>
                        </div>
                        <div className="Edit_Table_section_name" >
                            <input type="text" placeholder="Confirm your password"/>
                            {/* <RiLockPasswordLine /> */}
                        </div>
                    </div>
                    <div className="password_condition">
                        <input type="checkbox" />
                        <p>I agree with the Terms and <b>Conditions.</b></p>
                    </div>
                    <div className="settings-buttons Edit_Table_section_Button_Section">
                        <button onClick={handleClick}>Cancel</button>
                        <button>Save Changes</button>
                    </div>
            </div>
            </div>
            
            </div>
        </div>
    )
}
export default SettingsPage;