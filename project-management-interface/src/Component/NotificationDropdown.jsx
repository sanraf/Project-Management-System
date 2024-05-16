import './styles/NotificationDropDown.css';
import imageOne from "../assets/topnav-image1.jpg";
import { useState } from "react";

function NotificationDropDown(){

    const [data, setData] = useState([
        { 
            Id: 1, 
			Name:"Khangwelo",
            Description: "Presenting Hollywood Movie In English (English Movies, Hollywood Movies, Action Movies In English,  Adventure Movies In English, Scott Eastwood Movies In English) FAST CARS Exclusively on @blockbusterenglishmovies Sit back & enjoy ",
			Dates: "11:01 PM | October 3",
            opened: false 
		},{ 
            Id: 2, 
			Name:"Khangwelo Kevin",
            Description: "Check out the link in the description below.",
			Dates: "11:01 PM | October 3",
            opened: false
		}		
		,{ 
            Id: 3,
			Name:"Khangwelo Kevin Mamatho", 
            Description: "Remember, responsible betting is the name of the game.",
			Dates: "11:01 PM | October 3",
            opened: true
		}	
		,{ 
            Id: 4, 
			Name:"Khangwelo",
            Description: "000000000",
			Dates: "11:01 PM | October 3",
            opened: true
		}	
    ]);

	// Marking the text as green if opened and red if not opned
    const handleOpenMessage = (id) => {
        setData(prevData => {
            return prevData.map(item => {
                if (item.Id === id) {
                    return { ...item, opened: !item.opened };
                } else {
                    return item;
                }
            });
        });
    };


    //filtering if is opned or not
    const [showTrue, setShowTrue] = useState(true);
    const handleCheckedBoxChange =() =>{
        setShowTrue(!showTrue)
    }
    const filteredItems = data.filter(item =>
        item.opened === showTrue
    );
    
    return(
        <div className="NotificationDropDowncontainer">
            <div className="msg_header">
                <div className="msg-header-img">
                    <img src={imageOne} alt="" />
                </div>
                <div className="active">
                    <h4>Khangwelo Kevin Mamatho</h4>
                </div>
                <div className="header-icons">
                    <label className="switch">
                        <input type="checkbox" 
                        checked={showTrue}
                        onChange={handleCheckedBoxChange}
                        />
                        <span className="slider"></span>
                    </label>
                </div>
            </div>

            <div className="chat-page">
                <div className="msg-inbox">
                    <div className="chats">
                        <div className="msg-page">
                            {filteredItems.map(item => (
                                <div className="recieved-chats" key={item.Id} onClick={() => handleOpenMessage(item.Id)}>
                                    <div className="recieved-chats-img">
                                        <img src={imageOne} alt="" />
                                    </div>
                                    <div className="recieved-msg" >
                                        <div className="recieved-msg-inbox">
                                            <div className="paragrough_holder">
                                                <div className="content">
                                                <h5>
                                                    @{item.Name}
                                                </h5>
                                                <p>
                                                    {item.Description}
                                                </p>
                                                </div>
                                                <div 
                                                    className="State" 
                                                    style={{ backgroundColor: item.opened ? 'red' : '#339AF0' }} 
                                                >
                                                    <div className="State_color_Strip" style={{ color: item.opened ? 'red' : '#339AF0' }} >.</div>
                                                </div>
                                            </div>
                                            <span className="time">{item.Dates}</span>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default NotificationDropDown;
