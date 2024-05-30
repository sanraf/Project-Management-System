import { Link } from 'react-router-dom';
// import './styles/InsideNavBar_Style.css';
// import DropDown from './DropDown';
import { useState, useEffect } from 'react';

function InsideNavBar(){
    const [pageNav, setPageNav] = useState("0");
    
    const changeNavPositiononLoad = () => {
        if(window.location.href.includes("profileEditPage")) {
            setPageNav("25%")
        }
        if(window.location.href.includes("settingspage")) {
            setPageNav("0")
        }
        if(window.location.href.includes("notificationsettings")) {
            setPageNav("50%")
        }
        if(window.location.href.includes("feedbackpage")) {
            setPageNav("75%")
        }
    }

    useEffect(() => {
       changeNavPositiononLoad()
        return() => {
            
        }
    }, []);

    return(
        <div>  
            <div className="Settings_Icon_Holder">
                <h1>System Settings </h1>
                {/* <IoSettingsOutline className="Settings_Icon"/>
                <IoIosNotifications className="Settings_Icon" onClick={()=> setOpenProfile((prev)=> !prev)}/>
                {
                    openProfile && <DropDown />
                } */}
            </div>
            <nav>
                <Link onMouseLeave={changeNavPositiononLoad} onMouseOver={() => setPageNav("0")} to="/settingspage">Change Password</Link>
                 <Link onMouseLeave={changeNavPositiononLoad}  onMouseOver={()=>setPageNav("25%")} to="/profileEditPage">View Profile</Link>
                <Link onMouseLeave={changeNavPositiononLoad}  onMouseOver={()=>setPageNav("50%")} to="/notificationsettings">Notification</Link>
                <Link onMouseLeave={changeNavPositiononLoad}  onMouseOver={()=>setPageNav("75%")} to="/feedbackpage">Feedback</Link>
                {/* <Link to="/tableview">View Table</Link> */}
                <span  style={{left:pageNav}}></span>
            </nav>
        </div>
    );
}
export default InsideNavBar;