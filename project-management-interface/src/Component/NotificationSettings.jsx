// import './styles/Notification_Style.css';
import { BiEdit } from "react-icons/bi";
import InsideNavBar from './InsideNavBar';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import { useNavigate } from 'react-router-dom';
function NotificationSettings(){

    const navigate = useNavigate();
    const handleClick = () => {
        navigate('/project');
    }
    return(
        <div className="page-row">
        <Sidebar />
        <div className="project-wrapper">
          <Navbar />
        <div className="Notification_Container">
            <div className="Nav_Holder">
            <InsideNavBar />
            </div>
            <div className="Notification_Elements_Container_Holder">
            <div className="Notification_Elements_Container">
                <h1>Notifications</h1>
               <p> Manage your Notifications.</p>
               <p> Below are the notifications you may manage.</p>
               
            
            <div className="Notification_Table_section">
                <div className="Notification_Table_section_name"><p>Email notification</p>
                </div>
                <div className="Notification_Table_section_name">
                on
                <input type="radio" name="k" value="on"/>
                Off
                <input type="radio" name="k" value="off"/>
                </div>
                
            </div>
            <div className="Notification_Table_section">
                <div className="Notification_Table_section_name">
                <p>Send me email notification when a user comments on my blog</p>
                </div>
                <div className="Notification_Table_section_name">
                    on
                    <input type="radio" name="q" value="on"/>
                    Off
                    <input type="radio" name="q" value="off"/>
                </div>
            </div>
            <div className="Notification_Table_section">
                <div className="Notification_Table_section_name">
                <p>Send me email notification for the latest update</p>
                </div>
                <div className="Notification_Table_section_name">
                on
                <input type="radio" name="a" value="on"/>
                Off
                <input type="radio" name="a" value="off"/>
                </div>
            </div>
            <div className="Notification_Table_section">
                <div className="Notification_Table_section_name">
                <p>Send me email notification when a user sends me message</p>
                </div>
                <div className="Notification_Table_section_name">
                on
                <input type="radio" name="z" value="on"/>
                Off
                <input type="radio" name="z" value="off"/>
                </div>
            </div>
            
            <div className="Notification_Table_section_Button_Sction">
                <button onClick={handleClick}>Cancel</button>
                <button>Save Changes</button>
            </div>
            </div>
            </div>
            </div>
            
            </div>
        </div>
    )
}
export default NotificationSettings;