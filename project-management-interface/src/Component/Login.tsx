import React from 'react'
import loginImageOne from "../assets/login-image2.png"
import loginImageTwo from "../assets/login-image1.png"

function Login() {
  return (
      <>
          <div className="login_page">
              <div className="page-row">
                  <div className="user_login">
                      <div className="user_login_details">
                           <h5>Please register</h5>
                            <p>New to our system? please register your details to use the system</p>
                            <form action="">
                                <h6>Full name</h6>
                              <input id='fullname' type="text" placeholder='fullname' />
                              <h6>Email</h6>
                                <input type="text" id='email' placeholder='fullname' />
                               <h6>Password</h6>
                              <input id='password' type="text" placeholder='password' />
                              <h6>Re-enter password</h6>
                                <input id='password' type="text" placeholder='password' />
                                <button type='submit'>Register</button>
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