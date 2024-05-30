import BarChart from "./Barchart";
import { UserData } from "../Data";
import React, { useState } from 'react';
// import './styles/Feedback_Style.css';
import Navbar from './Navbar';
import InsideNavBar from "./InsideNavBar";
import Sidebar from './Sidebar';
import loginImageOne from "../assets/login-image2.png";
import loginImageTwo from "../assets/login-image1.png";

function FeedbackPage(){

    const [userData, setUserData] = useState({
        labels: UserData.map((data) => data.year),
        datasets: [{
          label: "Users Gained",
          data: UserData.map((data) => data.userGain),
          backgroundColor : ["red","green", "Orange", "blue"],
        //   borderColor: "black",
        // borderWidth: 1,
        },
        
      ],
        
      })




    return(
        <div className="page-row">
            <Sidebar />
            <div className="project-wrapper">
                <Navbar />
                <div className="Feedback_Container settings_container">
                    <InsideNavBar />
                    <div className="Feedback_Container_Holder settings_sections">
                        <div className="settings_header">
                            <h1><i className="lni lni-comments-reply"></i>PMS Feed Back</h1>
                            <p>Please provide any feedback you would like to mention about the system. Will be sure to address any concerns stated. </p>
                        </div>
                        <div className="Feedback_Container_Holder_Body_LeftSide">
                        <form action="">
                                <p>Name :</p>
                                <input type="text" placeholder="fullname"/>
                                <p>Email :</p>
                                <input type="text" placeholder="enter email"/>
                                <p>Subject :</p>
                                <input type="text" placeholder="enter subject"/>
                                <p>Message :</p>
                                <textarea name="" placeholder="enter message" id=""></textarea>
                                <div className="settings-buttons send-feedback">
                                    <button>Send feedback</button> 
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    );
}
export default FeedbackPage;