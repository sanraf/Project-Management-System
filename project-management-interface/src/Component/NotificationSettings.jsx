// import './styles/Notification_Style.css';
import { useState,useEffect } from 'react';
import InsideNavBar from './InsideNavBar';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import { useNavigate } from 'react-router-dom';
import axios from "axios";


function NotificationSettings() {
    const [projectEmailOn, setProjectEmailOn] = useState(0);
    const [projectEmailOff, setProjectEmailOff] = useState(0);
    const [taskEmailOn, settaskEmailOn] = useState(0);
    const [taskEmailOff, settaskEmailOff] = useState(0);

    const navigate = useNavigate();
    const handleClick = () => {
        navigate('/project');
    }

    const defaultNotiSettings = async () => {
        try {
            const response = await axios.get("http://localhost:8080/notification/getNotifSettings", {
                withCredentials:true
            });
            if(response.data !== "could not get user") {
                settaskEmailOn(response.data.taskNotification)
                setProjectEmailOn(response.data.projectNotification)

                if(response.data.taskNotification) {
                    settaskEmailOff(0)
                } else {settaskEmailOff(1)}
                
                if(response.data.projectNotification) {
                    setProjectEmailOff(0)
                }else{setProjectEmailOff(1)}
            }
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }

    useEffect(() => {
        defaultNotiSettings();

        return() => {
        }
    }, []);

    const changeRadioButton = (btnType) => {
        if (btnType == "project_on") {
            setProjectEmailOn(1)
            setProjectEmailOff(0)
        }
        if (btnType == "project_off") {
            setProjectEmailOn(0)
            setProjectEmailOff(1)
        }
        if (btnType == "task_on") {
            settaskEmailOn(1)
            settaskEmailOff(0)
        }
         if (btnType == "task_off") {
            settaskEmailOn(0)
            settaskEmailOff(1)
        }
    }

    const sendNotificationSettings = async (e, btnType) => {
        e.preventDefault();
        if (btnType == "send") {
            const notifiSettings = {taskNotification: taskEmailOn, projectNotification: projectEmailOn}
            try {
                const response = await axios.post(`http://localhost:8080/notification/sendNotifiSettings`, notifiSettings, {
                    withCredentials: true
                });
                if (response.data) {
                    console.log(response.data)
                    window.location.reload();
                }
                } catch (error) {
                    console.error("Error creating project: ", error);
            }
        } else {
            defaultNotiSettings();
        }
        
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
                        <form action="">
                            <div className="settings_table_section">
                                <div className="Notification_Table_section_name"><p>Project creation notification email</p>
                                </div>
                                <div className="Notification_Table_section_name noti-radio-buttons">
                                    <p>on</p>
                                    <input checked ={projectEmailOn} type="radio" onChange={()=>changeRadioButton("project_on")} />
                                    <p>off</p>
                                    <input checked ={projectEmailOff} type="radio"  onChange={()=>changeRadioButton("project_off")}/>
                                </div>
                            </div>
                            <div className="settings_table_section">
                                <div className="Notification_Table_section_name"><p>Task deadline notification email</p>
                                </div>
                                <div className="Notification_Table_section_name noti-radio-buttons">
                                    <p>on</p>
                                    <input checked={taskEmailOn}  type="radio"  onChange={()=>changeRadioButton("task_on")}/>
                                    <p>off</p>
                                    <input checked={taskEmailOff} type="radio"  onChange={()=>changeRadioButton("task_off")}/>
                                </div>
                            </div>
                            <div className="settings-buttons Notification_Table_section_Button_Section">
                                <button onClick={(e)=>sendNotificationSettings(e,"cancel")}>Cancel</button>
                                <button onClick={(e)=>sendNotificationSettings(e,"send")}>Save Changes</button>
                            </div>
                        </form>
                </div>
            </div>
            
            </div>
        </div>
    )
}
export default NotificationSettings;