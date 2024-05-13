import React, { useState } from 'react';
import { BiEdit } from "react-icons/bi";
import { CiViewTable } from "react-icons/ci";
import { FaProjectDiagram, FaTasks } from "react-icons/fa";
// import './styles/ProfileEditPage_Style.css';
import { useNavigate } from 'react-router-dom';
import InsideNavBar from './InsideNavBar';

import Navbar from './Navbar';
import Sidebar from './Sidebar';

function ProfileEditPage() {
    const [userData, setUserData] = useState({
        Id: 1,
        UserName: "Khangwelo Kevin",
        Number_Of_Tables: 7,
        Number_Of_Projects: 8,
        Number_Of_Tasks: 6
    });
    const navigate = useNavigate();

    const redirectTo = () => {
        navigate('/settingspage');
    };

    const handleClick = () => {
        navigate('/project');
    };

    const handleChange = (event, field) => {
        const value = event.target.value;
        setUserData(prevData => ({
            ...prevData,
            [field]: value
        }));
    };

    return (
        <div className="page-row">
            <Sidebar />
            <div className="project-wrapper">
                <Navbar />
                <div className="View_Container">
                    <div className="Nav_Holder">
                        <InsideNavBar />
                    </div>
                    <div className="View_Elements_Container_Holder">
                        <div className="View_Elements_Container">
                            <h1>View Profile</h1>
                            <p> Manage your Name, and Email Addresses</p>
                            <p> Below are the name and email addresses on file for your account.</p>


                            <div className="View_Table_section">
                                <div className="View_Table_section_name"><h4>Your UserName:</h4></div>
                                <div className="View_Table_section_name"><input type="text" value={userData.UserName} onChange={(e) => handleChange(e, "UserName")} /></div>
                                <div className="View_Table_section_name"><BiEdit className="icon" onClick={redirectTo} /></div>
                            </div>
                            <div className="View_Table_section">
                                <div className="View_Table_section_name"><h4>Your Number of Tables</h4></div>
                                <div className="View_Table_section_name"><input type="text" value={userData.Number_Of_Tables} onChange={(e) => handleChange(e, "Email")} /></div>
                                <div className="View_Table_section_name"><CiViewTable className="icon" /></div>
                            </div>
                            <div className="View_Table_section">
                                <div className="View_Table_section_name"><h4>Your Number of projects</h4></div>
                                <div className="View_Table_section_name"><input type="text" value={userData.Number_Of_Projects} onChange={(e) => handleChange(e, "Number_Of_Projects")} /></div>
                                <div className="View_Table_section_name"><FaProjectDiagram className="icon" /></div>
                            </div>
                            <div className="View_Table_section">
                                <div className="View_Table_section_name"><h4>Your Number of Tasks</h4></div>
                                <div className="View_Table_section_name"><input type="text" value={userData.Number_Of_Tasks} onChange={(e) => handleChange(e, "Number_Of_Tasks")} /></div>
                                <div className="View_Table_section_name"><FaTasks className="icon" /></div>
                            </div>
                            <div className="View_Table_section_Button_Sction">
                                <button onClick={handleClick}>Cancel</button>
                                {/* <button>Save Changes</button>  */}
                                <button>Delete Account</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ProfileEditPage;
