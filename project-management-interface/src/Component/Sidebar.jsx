
import React,{useEffect,useState} from 'react'
import logo from "../assets/logo.png";
import axios from 'axios';
import Cookies from 'js-cookie';

function Sidebar() {
  const [project, setProject] = useState([]);
  
  useEffect(() => { 
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/user/fetchUserProject`, {
          withCredentials:true
        });
        if(response.data) {
          console.log(response.data)
        }
        } catch (error) {
          console.error('Error fetching data:', error);
      }
    };
    fetchData();
  },[]);
  const loadProject =(project_id)=>{
    sessionStorage.setItem("projectId",project_id);
    window.location.href = "project";
  }
  return (
    <>
    <div className="sidebar">
        <h6 className='logo'><img src={logo} alt="" /><span> ProjectGuru</span></h6>
        <a href='/createproject' id='sidebar-create-btn'>Create project <i className="lni lni-plus"></i></a>
        <div className="sidebar-links">
          <span className='sidebar-subtitle'>dashboard</span>
          <a href=""><i className="lni lni-briefcase"></i> User Projects</a>
            {
              project ? project.map((item,project_index)=>
                <p className='sub-links' key={project_index} onClick={()=>loadProject(item.project_id)}> {item.title} </p>
              ):""
          }
          <div className='admin_dash'>
            <span className='sidebar-subtitle'>Adminstartion</span>
            <a href="users"><i className="lni lni-users"></i>Users</a>
            <a href=""><i className="lni lni-briefcase-alt"></i>Project stats</a>
            <a href=""><i className="lni lni-cogs"></i>System usage</a>
            <a href=""><i className="lni lni-check-box"></i>Feedback</a>
          </div>
          <a href="/"><i id='info' className=" lni lni-information"></i> Guide </a>
          
        </div>
    </div>
    </>
  )
}

export default Sidebar