import React,{useEffect,useState} from 'react'
import imageOne from "../assets/email_password.png";
import axios from 'axios';
import { useLocation } from 'react-router-dom';

const useQuery = () => {
    return new URLSearchParams(useLocation().search)
}

function Privacy() {
    const [email, setEmail] = useState(false);
    const [userEmail, setUserEmail] = useState();
    const [newPassword, setnewPassword] = useState();
    const [passwordCopy , setPasswordCopy] = useState();
    const query = useQuery();
    const token = query.get('token');
 
    useEffect(() => {
        const emailRequest = sessionStorage.getItem("sentEmail");
        if (emailRequest == "requestedEmail") {
            setEmail(true)
        }
        return () => {
            setEmail(false)
        }
    }, []);
    
    const sendNewPassword = async (e) => {
        e.preventDefault();
        if (newPassword == passwordCopy) {
            try {
                const response = await axios.post(`http://localhost:8080/auth/changeForgotPassword/${token}/${newPassword}`);
                if(response.data == "password successfully saved") {
                    window.location.href = "/";
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
    }

    const resetPasswordEmail = async (e) => {
        e.preventDefault();
        if (userEmail) {
            try {
                const response = await axios.post(`http://localhost:8080/auth/userPasswordReset?email=${userEmail}`);
                if(response.data) {
                    console.log(response.data)
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
    }

  return (
      <>
          <div className="privacy_section">
              <div className="privacy_login_box"  >
                  <div className="privacy_title">
                      <img src={imageOne} alt="" />
                      <div className='privacy_password_box' style={{display: !email ? "block":"none"}}>
                          <h6>Forgot your password?</h6>
                          <p>Please enter your new password before login</p>
                          <form className="privacy_password_form" action="">
                              <label htmlFor="">Password</label>
                              <input type="text" required onChange={(e)=>setnewPassword(e.target.value)}/>
                              <label htmlFor="">Re-enter password</label>
                              <input type="text" required onChange={(e)=>setPasswordCopy(e.target.value)}/>
                              <input type="submit" onClick={(e)=>sendNewPassword(e)} value="reset password"/>
                          </form>
                      </div>
                      <div style={{display: email ? "block":"none"}}>
                            <h6>Trouble logging in ?</h6>
                            <p>Please enter your email and we will sent you a
                                link to reset your password </p>
                            <form className='password_email' action="">
                                <label htmlFor="">email</label>
                                <input type="text" onChange={(e)=>setUserEmail(e.target.value)} placeholder='Please enter your email' />
                                <div>
                                    <input type="submit" onClick={e=>resetPasswordEmail(e)} value="Send Email" name="" id="" />
                                    <p>OR</p>
                                    <a href=''>create new account</a>
                                </div>
                            </form>
                      </div>
                  </div>
                  <a href='/' id='privacy_login_link'>Return to <span>login</span></a>
              </div>
          </div>
      </>
  )
}

export default Privacy