import BarChart from "./Barchart";
import { UserData } from "../Data";
import React, { useState } from 'react';
// import './styles/Feedback_Style.css';
import Navbar from './Navbar';
import InsideNavBar from "./InsideNavBar";
import Sidebar from './Sidebar';
import loginImageOne from "../assets/login-image2.png";
import loginImageTwo from "../assets/login-image1.png";
import { useForm } from "react-hook-form";


function FeedbackPage() {
    const [feedbackMessage, setfeedbackMessage] = useState("");

    const {register, handleSubmit} = useForm({
        shouldUseNativeValidation: true,
        defaultValues: {
            fullName: "",
            email: "",
            subject: "",
            message:""
        }
    })

    const sendFeedback = async (userMessage) => {
        try {
            const response = await axios.post(`http://localhost:8080/user/sendFeedback`, userMessage, {
                withCredentials: true
            });
            if (response.data == "feedback successfully saved") {
                
            }
            } catch (error) {
                console.error("Error creating project: ", error);
        }
    }

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
                            <form onSubmit={handleSubmit(sendFeedback)} action="">
                                <p>Name :</p>
                                <input {...register("fullName")} type="text" required name="fullName" placeholder="fullname"/>
                                <p>Email :</p>
                                <input {...register("email")} type="text" required name="email" placeholder="enter email"/>
                                <p>Subject :</p>
                                <input {...register("subject")} type="text" required name="subject" placeholder="enter subject"/>
                                <p>Message :</p>
                                <textarea {...register("message")} name="message" required placeholder="enter message" id=""></textarea>
                                <div className="settings-buttons send-feedback">
                                    <button>Send feedback</button> 
                                </div>
                            </form>
                            <p>{feedbackMessage}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    );
}
export default FeedbackPage;