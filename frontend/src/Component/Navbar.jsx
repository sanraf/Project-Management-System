import React from 'react'
import imageOne from "../assets/topnav-image1.jpg"

function Navbar() {
  return (
    <>
    <div className="navbar">
        <div className="page-row">
            <form action=""><input type="text" placeholder='Search here'/>
            <button id='input_search_btn'><i className="lni lni-search"></i></button></form>
            <div className="nav-right-bar">
                <div className="nav-icons">
                    <i class="lni lni-information"></i>
                    <i className="lni lni-alarm"></i>
                    <i class="lni lni-cog"></i>
                </div>
                <div className="profile">
                  <div>
                    <span>owner</span>
                    <span> John Doe</span>
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