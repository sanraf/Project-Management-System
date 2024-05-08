import React, { useEffect, useState } from 'react';
import axios from 'axios'; 
import imageOne from "../assets/topnav-image1.jpg";


function Navbar() {
  const [siteUser, setSiteUser] = useState();
  const [name, setName] = useState();
  const [logUser, setLogUser] = useState();
  const [userId, setUserId] = useState();
  const [dropDownBoxesHeight, setDropDownBoxesHeight] = useState({
    statusBox: 0, addColumn: 0, columnIndex: 0, editRow: 0, inviteBox: 0,
    filterBox: 0, sort: 0, rowId: 0, statusIndex: 0, table: 0, tableIndex: 0,
    commentBox: 0, commentTaskId: 0, assigneeHieght: 0, assigneeId: 0
  });

  useEffect(() => {
    setSiteUser(JSON.parse(sessionStorage.getItem("systemUser")));
    const foundUser = (JSON.parse(sessionStorage.getItem("systemUser")));
    setLogUser(foundUser)
    return () => {
    }
  }, []);

  const toogleDropDownBoxes = (contentType, height, rowIndex, indexType) => {
    setDropDownBoxesHeight(prevState => ({
      ...prevState,
      [contentType]: dropDownBoxesHeight[contentType] === 0 ? height : 0,
      [indexType !== "" ? indexType : '']: rowIndex
    }));
    const ame = siteUser.fullname
    const letter = ame.charAt(0)
    console.log(letter)
    setName(letter)
  }

  const handleDeactivateAccount = (e) => {
    e.preventDefault(); 
     setUserId(siteUser.user_id)

    // Send an AJAX request to deactivate the account
    axios.put(`/user/deactivate/${userId}`,{}, { 
      headers: {
        Authorization: `Bearer ${logUser.token}`, // Assuming token is stored in a variable
        'Content-Type': 'application/json'
    }
     })
      .then(response => {
        // Handle success response if needed
        console.log('Account deactivated successfully');
      })
      .catch(error => {
        // Handle error response if needed
        console.error('Error deactivating account:', error);
        console.log(userId)
      });
  };

  return (
    <>
      <div className="navbar">
        <div className="page-row">
          <form action=""><input type="text" placeholder='Search here' />
            <button id='input_search_btn'><i className="lni lni-search"></i></button></form>
          <div className="nav-right-bar">
            <div className="nav-icons">
              <i className="lni lni-information"></i>
              <i className="lni lni-alarm"></i>
              <i className="lni lni-cog"></i>
            </div>
            <div className="profile" >
              <div>
                <span>{siteUser ? siteUser.fullname : ""}</span>
                <span>{siteUser ? siteUser.email : ""}</span>
              </div>
              <img src={imageOne} onClick={() => toogleDropDownBoxes("inviteBox", 380, 0, "")} alt="" />
              <div style={{ height: dropDownBoxesHeight.inviteBox }} className="invite_members">
                <div className="member_invite_wrapper">
                  <h5 style={{ fontWeight: "bold" }}>ACCOUNT</h5>
                  <div style={{ position: "relative" }}>
                    <div className="project_create_table_invite">
                      <div className="project_members">
                        <span style={{ color: "#339AF0", fontSize: "13px", textTransform: "uppercase" }}>{name}</span>
                      </div>
                      <span>{siteUser ? siteUser.fullname : ""}</span>
                      <span>{siteUser ? siteUser.email : ""}</span>
                    </div>
                  </div>
                  <a href='/' style={{ textDecoration: "none", fontSize: "13px", color: "#333", display: "block", marginTop: "1rem" }}>Switch account</a>
                  <div style={{ border: "0.1px solid #ddd", marginTop: "7px" }}></div>
                  <h5 style={{ fontWeight: "bold" }}>ProjectGuru</h5>
                  <div className="sidebar-links">
                    <a href='/profilepage' >Profile and Visibility</a>
                    <a>Activity</a>
                    <a href='#' onClick={handleDeactivateAccount} >Deactivate account</a>
                    <a>Settings</a>
                    <div style={{ border: "0.1px solid #ddd", marginTop: "7px" }}></div>
                    <a href='/'>Logout</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Navbar;
