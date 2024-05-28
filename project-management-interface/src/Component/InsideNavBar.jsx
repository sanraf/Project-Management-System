import { Link } from 'react-router-dom';
// import './styles/InsideNavBar_Style.css';
// import DropDown from './DropDown';
import { useState } from 'react';

function InsideNavBar(){
    const [openProfile, setOpenProfile] = useState(false);
    return(
        <div>  
            <div className="Settings_Icon_Holder">
                <h1>Settings </h1>
                {/* <IoSettingsOutline className="Settings_Icon"/>
                <IoIosNotifications className="Settings_Icon" onClick={()=> setOpenProfile((prev)=> !prev)}/>
                {
                    openProfile && <DropDown />
                } */}
            </div>
            <nav>
            <Link to="/profileEditPage">Edit Profile</Link>
            <Link to="/settingspage">Change Password</Link>
            <Link to="/notificationsettings">Notification</Link>
            <Link to="/feedbackpage">Feedback</Link>
            {/* <Link to="/tableview">View Table</Link> */}
            <span></span>
        </nav>
        </div>
    );
}
export default InsideNavBar;