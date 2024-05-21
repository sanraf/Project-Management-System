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
        <div className="Feedback_Container">
            <InsideNavBar />
            <div className="Feedback_Container_Holder">
                <div className="Feedback_Container_Holder_Heading">
                    <h1>PMS Feed Back</h1>
                </div>
                
                <div className="Feedback_Container_Holder_Body_Holder">
                <div className="Feedback_Container_Holder_Body_LeftSide">
                    <div className="Feedback_Container_Holder_Body_LeftSide_Elements" >
                    <p>Name :</p>
                    <input type="text" />
                    <p>Email :</p>
                    <input type="text" />
                    <p>Subject :</p>
                    <input type="text" />
                    <p>Message :</p>
                    <textarea type="text" />
                    <br></br>
                    <br></br>
                    <br></br>
                    <button>Submit</button>
                    </div>
                </div>
                <div className="Feedback_Container_Holder_Body_RightSide">
                    <div className="Feedback_Container_Holder_Body_RightSide_Image">
                        Image
                    </div>
                    <div className="Feedback_Container_Holder_Body_RightSide_Graph">
                    <BarChart chartData={userData}/>
                

                    </div>
                </div>
                </div>
                </div>
                </div>
                
                
            </div>
        </div>
        
    );
}
export default FeedbackPage;