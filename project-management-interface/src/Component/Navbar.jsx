import React,{useEffect,useState} from 'react'
import imageOne from "../assets/topnav-image1.jpg"

function Navbar() {
  const [siteUser, setSiteUser] = useState();
  useEffect(() => {
    setSiteUser(JSON.parse(sessionStorage.getItem("systemUser")));
    return()=>{
    }
  }, []);
  return (
    <>
    <div className="navbar">
        <div className="page-row">
            <form action=""><input type="text" placeholder='Search here'/>
            <button id='input_search_btn'><i className="lni lni-search"></i></button></form>
            <div className="nav-right-bar">
                <div className="nav-icons">
                    <i className="lni lni-information"></i>
                    <i className="lni lni-alarm"></i>
                    <i className="lni lni-cog"></i>
                </div>
                <div className="profile">
                <div>
                 <span>{siteUser?siteUser.fullname:""}</span>
                  <span>{siteUser?siteUser.email:""}</span>
                </div>
                  <img src={imageOne} alt="" />
                </div>
            </div>
        </div>
    </div>
    </>
  )
}

export default Navbar