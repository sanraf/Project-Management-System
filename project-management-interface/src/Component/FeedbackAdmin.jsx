import { Title } from "chart.js";
// import './styles/FeedbackAdmin.css';
import Sidebar from "./Sidebar";
import Navbar from "./Navbar";


function FeedbackAdmin(){
    const data = [
        { id: 1,
            Title:"Name",
            feedback: "If you want the container to scroll up and down rather than left and right" },
        { id: 2,
            Title:"Name",
            feedback: "If you want the container to scroll up and down rather than left and right" },
        { id: 3,
            Title:"Name",
            feedback: "If you want the container to scroll up and down rather than left and right" },
        { id: 4,
            Title:"Name",
            feedback: "If you want the container to scroll up and down rather than left and right" },
        { id: 5,
            Title:"Name",
            feedback: "If you want the container to scroll up and down rather than left and right" }
    ];
    return(
        <div className="page-row">
        <Sidebar />
        <div className="project-wrapper">
          <Navbar />
        <div className="Feed_Container">
            <center><h1>Feedback </h1></center>
        <div class="card">
            <div className="title">
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            </div> 
            <div className="card__tags__Holder">
                <div class="card__tags">
                    <ul class="tag">
                    {data.map(item => (
                        <li key={item.Id} class="tag__name">
                            <div>
                            <h3>{item.Title}</h3>
                            <p>{item.feedback}</p>
                            </div>
                        </li>
                        ))}
                    </ul>
                </div>
            </div>
            </div>
        </div>
        </div>
        </div>
    )
}
export default FeedbackAdmin;