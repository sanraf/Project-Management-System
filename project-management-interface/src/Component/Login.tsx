import React,{useState} from 'react'
import loginImageOne from "../assets/login-image2.png";
import loginImageTwo from "../assets/login-image1.png";
import axios from 'axios';
import { useForm } from 'react-hook-form';

function Login() {
    const [showRegister, setShowRegister] = useState("none");
    const { register, handleSubmit, setValue } = useForm({
        shouldUseNativeValidation:true,
        defaultValues:{
         fullname: "",  
         email:"",
         password:"",
      }
    });
    
    const login = async(userDetails) => {
        if (showRegister == "none") {
            const basicAuth = 'Basic ' + btoa(userDetails.email + ':' + userDetails.password); // Creating Basic Authorization header
            try {
                
                const response = await axios.post(`http://localhost:8080/auth/login`,userDetails);
                if (response.data.statusCode == "401") {
                    console.log(response.data.status)
                    setShowRegister("block")
                } else {
                    const userDetails = { email: response.data.email,token:response.data.token, fullname: response.data.fullname,user_id:response.data.userId}
                    sessionStorage.setItem("systemUser",JSON.stringify(userDetails))
                    window.location.href = "help"
                    console.log(userDetails)
                }
                } catch (error) {
                    console.error("Error adding task: ", error);
            }
        } else {
            const editedUserDetails = { ...userDetails }; 
            editedUserDetails.dateRegistered = new Date();
            editedUserDetails.roles = [];
            try {
                const response = await axios.post(`http://localhost:8080/auth/registerUser`,editedUserDetails);
                if (response.data) {
                    setShowRegister("none")
                }
                } catch (error) {
                    console.error("Error adding task: ", error);
                }
        }
    }

    return (
      <>
          <div className="login_page">
              <div className="page-row">
                  <div className="user_login">
                      <div className="user_login_details">
                            <div>
                                <h5 style={{display:showRegister}}>Please Register before login</h5>
                                <p style={{ display: showRegister }}>New to our system? please register your
                                details to use the system</p>
                            </div>
                            <div style={{display:showRegister == "block"?"none":"block"}}>
                                <h5 >Please Login</h5>
                                <p>Thank you for choosing us, if registered please login</p>
                            </div>
                          <a href="">Register on this page</a>
                            <form action="" onSubmit={handleSubmit(login)} >
                              <div style={{display:showRegister}}>
                                <h6>Full name</h6>
                                <input  {...register("fullname",{required: showRegister == "block" ? true:false})} id='fullname' name ='fullname' type="text" placeholder='fullname' />
                              </div>
                              <h6>Email</h6>
                                <input {...register("email",{required:"please enter email"})} type="text" id='email' name="email"  placeholder='email' />
                               <h6>Password</h6>
                              <input  {...register("password",{required:"please enter password"})} id='password' type="text" name="password" placeholder='password' />
                              <div style={{display:showRegister}}>
                                <h6>Re-enter password</h6>
                                <input id='password' type="text" placeholder='password' />
                               </div>
                              <button type='submit'>{showRegister == "block " ? "Register" : "Login" }</button>
                            </form>
                            <div className="third_party_login">
                                <p id=''>Login with other sites</p>
                                 <i className="lni lni-github-original"></i>
                                <i className="lni lni-google"></i>
                            </div>
                      </div>
                  </div>
                  <div className="login_project_description">
                      <div className="login_project_title">
                          <h1>Hello Welcome, to Project Name</h1>
                          <p>Hello and welcome to ProjectMaster! We're delighted to have you join
               our community of project managers and teams who are achieving remarkable success with our powerful project
                management platform.
              </p>
                      </div>
                      <div className="login_page_images">
                          <img src={loginImageTwo}id='login_image_one' alt="" />
                          <img src={loginImageOne} id='login_image_two' alt="" />
                      </div>
                  </div>
              </div>
          </div>
      </>
  )
}

export default Login