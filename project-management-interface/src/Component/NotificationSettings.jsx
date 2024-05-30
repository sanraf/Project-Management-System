// import './styles/Notification_Style.css';
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
        <div className="Notification_Container settings_container">
            <div className="Nav_Holder">
            <InsideNavBar />
            </div>
               <div className="Notification_Elements_Container settings_sections">
                    <div className="settings_header">
                        <h1><i className="lni lni-alarm"></i>Notifications</h1>
                        <p> Manage your Notifications.</p>
                        <p> Below are the notifications you may manage.</p>
                    </div>
                    <div className="table_section">
                        <div className="Notification_Table_section_name"><p>Project creation notification email</p>
                        </div>
                        <div className="Notification_Table_section_name noti-radio-buttons">
                            <p>on</p>
                            <input type="radio" name="k" value="on"/>
                            <p>off</p>
                            <input type="radio" name="k" value="off"/>
                        </div>
                     </div>
                    <div className="table_section">
                        <div className="Notification_Table_section_name"><p>Task deadline notification email</p>
                        </div>
                        <div className="Notification_Table_section_name noti-radio-buttons">
                            <p>on</p>
                            <input type="radio" name="k" value="on"/>
                            <p>off</p>
                            <input type="radio" name="k" value="off"/>
                        </div>
                    </div>
                            
                    <div className="settings-buttons Notification_Table_section_Button_Section">
                        <button onClick={handleClick}>Cancel</button>
                        <button>Save Changes</button>
                    </div>
                </div>
            </div>
            
            </div>
        </div>
    )
}
export default NotificationSettings;