import React from 'react'
import loginImageOne from "../assets/login-image2.png"
import loginImageTwo from "../assets/login-image1.png"

function Login() {
  return (
      <>
          <div className="login_page">
              <div className="page-row">
                  <div className="user_login">
                      
                  </div>
                  <div className="login_project_description">
                      <div className="login_project_title">
                          <h1>We present to you name of project</h1>
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