import React, { useState, useEffect } from 'react';
import Sidebar from './Sidebar';
import Navbar from './Navbar';
import imageOne from "../assets/topnav-image1.jpg"


const ProfilePage = () => {
 
  const [fullname, setFullname] = useState('');
  const [password, setPassword] = useState('');
  const [siteUser, setSiteUser] = useState();
 
  

  useEffect(() => {
    const user = JSON.parse(sessionStorage.getItem("systemUser"));
    if (user) {
      setSiteUser(user);
      setFullname(user.email); // Set fullname directly inside the callback
    }
  }, []);
  


  

  const handleFullnameChange = (e) => {
    setFullname(e.target.value);
  };
  
 
  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };
  

  // Function to handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    
    console.log("Fullname:", fullname);
    console.log("Password:", password);
    
  };
  
  return (
    <div className="page-row">
    <Sidebar/>
    <div className="project-wrapper">
        <Navbar />
        <div style={{backgroundColor:"white", paddingTop:"20px", paddingBottom:"170px"}}>
        <div style={{display:"flex" , marginTop:"50px", marginLeft:"15px" , fontFamily:"Roboto"}}>
        <img src={imageOne} alt='' style={{width:"50px", height:"50px" , borderRadius:"100%"}}/>
        <div style={{display:"block" , textAlign:"left",marginTop:"12px", marginLeft:"10px"}}>
        <span style={{display:"block"}}>{siteUser?siteUser.fullname:""}</span>
        <span>{siteUser?siteUser.email:""}</span>
        </div>
        </div>
        <div className="user_profile" style={{ textAlign: "center", marginTop:"100px", fontFamily:"Roboto"}}>
        <div style={{ border: "0.1px solid #ddd", marginTop: "7px" }}></div>
            <div className="user_login_details">
                <h6 style={{  fontSize:"20px", marginLeft:"350px" }}>Manage your personal information</h6>
                <form onSubmit={handleSubmit}>
                <div style={{ textAlign: "center", display:"inline-block" }}>
                    <div>
                        <label style={{ marginRight:"100px" }}>Username:</label>
                        <input type="text" value={fullname} onChange={handleFullnameChange} style={{ marginLeft:"200px" }} />
                    </div>
                    <div>
                        <label style={{ marginRight:"100px" }}>Password:</label>
                        <input type="password" value={password} onChange={handlePasswordChange} style={{ marginLeft:"200px" }} />
                    </div>
                    <div>
                        <label style={{ marginRight:"48px" }}>Confirm Password:</label>
                        <input type="password" value={password} onChange={handlePasswordChange} style={{ marginLeft:"200px" }} />
                    </div>
                    <button type="submit" style={{ marginLeft:"370px" }}>Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
  );
};

export default ProfilePage;
