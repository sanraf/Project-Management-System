
import React,{useEffect,useState} from 'react'
import logo from "../assets/logo.png";
import axios from 'axios';

function Sidebar(props) {
  const [project, setProject] = useState([]);
  
  useEffect(() => { 
    const fetchData = async () => {
      const systemUser = JSON.parse(sessionStorage.getItem("systemUser"));
      if (systemUser) {
        try {
          const response = await axios.get(`http://localhost:8080/user/fetchUserProject`, {
            headers: {
              Authorization: `Bearer ${systemUser.token}`, // Assuming token is stored in a variable
              'Content-Type': 'application/json'}
            });
            setProject(response.data);
          } catch (error) {
            console.error('Error fetching data:', error);
        }
      }
    };
    fetchData();
  },[]);
  const loadProject =(event,project_id)=>{
    event.preventDefault();
    sessionStorage.setItem("projectId",project_id);
    window.location.href = "project";
  }
  return (
    <>
    <div className="sidebar">
        <h6 className='logo'><img src={logo} alt="" /><span> ProjectGuru</span></h6>
        <a href='/createproject' id='sidebar-create-btn'>Create project <i className="lni lni-plus"></i></a>
        <div className="sidebar-links">
            <span id='sidebar-subtitle'>dashboard</span>
            {
              project ? project.map((item,project_index)=>
                <a key={project_index} href="" onClick={(event)=>loadProject(event,item.project_id)}><i className="lni lni-briefcase"></i> {item.title} </a>
              ):""
            }
            <a href="/">  <i id='info' className=" lni lni-information"></i> Guide </a>
        </div>
    </div>
    </>
  )
}

export default Sidebar