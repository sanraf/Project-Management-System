import React,{useState,useEffect} from 'react'
import axios from 'axios';
import Comment from './Comment';
import Assign from './Assign';

function ProjectSection() {
    const [editedTask, seteditedTask] = useState({});
    const [hiddenColumns, sethiddenColumns] = useState({priority:"none"});
    const [projectMembers, setProjectMembers] = useState(["Jane Doe"]);
    const [users, setUsers] = useState(["Alex Smith"]);
    const [memeberFound, setmemeberFound] = useState([]);
    const [loginMemberId, setloginMemberId] = useState();
    const [loggedInUser, setloggedInUser] = useState();
    const [taskComments, settaskComments] = useState([{ comment_id: 0, username: "", date_created: "", commentBody: "" }]);
    const [assignees, setassignees] = useState([]);
    const [dropDownBoxesHeight, setDropDownBoxesHeight] = useState(
        {
            statusBox: 0, addColumn: 0, columnIndex: 0, editRow: 0, inviteBox: 0,
            filterBox: 0, sort: 0, rowId: 0, statusIndex: 0, table: 0, tableIndex: 0,
            commentBox: 0, commentTaskId:0, assigneeHieght: 0, assigneeId:0
        }
    );

    const [oneProject, setoneProject] = useState({
        project_id: 0,
        title: "",
        description: "",
        tables: [
            {
                table_id: 0,
                table_name: "New Table",
                tasks: [
                    {
                        task_id:0,
                        title:"",
                        description:"",
                        owner:"",
                        start_date:"",
                        end_date:"",
                        status: "",
                        comments: [],
                        assignees: []
                    }
                ]
            }
        ],
        memberList: [
            {
                member_id: 0,
                user_id: 0,
                project_id: 0,
                username: ""
            }
        ]
    });
   
    useEffect(() => {
        const projectId = sessionStorage.getItem("projectId");
        const foundUser = (JSON.parse(sessionStorage.getItem("systemUser")));
        setloggedInUser(foundUser);
        
        const fetchProject = async () => {
          
            try {
                const response = await axios.get(`http://localhost:8080/user/getSingleProject/${projectId}`, {
                    headers: {
                        Authorization: `Bearer ${foundUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data) {
                    setoneProject(response.data);
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        
        };
        if(projectId){
            fetchProject();
        }
       
        return () => {
        }
    }, []);
 
    const toogleDropDownBoxes=(contentType,height,rowIndex,indexType)=>{
        setDropDownBoxesHeight(prevState => ({
        ...prevState,
        [contentType]: dropDownBoxesHeight[contentType] === 0 ? height : 0,
        [indexType !== "" ? indexType : '']: rowIndex
    }));} 
    const createTable = async (projectNo) => {
     console.log(loggedInUser.token)
        if (loggedInUser) {
            try {
                const response = await axios.post(`http://localhost:8080/table/createTable/${projectNo}`,{}, {
                    headers: {
                        Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data.project_id) {
                    window.location.reload
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
    } 
    const sendEditedRow = async (task, updatedValue,columnType) => {
        let taskToSend = {}
        
        if (Object.keys(editedTask).length !== 0) {
            taskToSend = { ...editedTask }
        }
        if (columnType == 'status') {
            taskToSend = {...task, status: updatedValue}
        }
        if (loggedInUser) {
            try {
                const response = await axios.put(`http://localhost:8080/task/editTask`,JSON.stringify(taskToSend), {
                    headers: {
                        Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data) {
                    window.location.reload()
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
           
    }
    const Adding_Task = async (table_id) => {
        if(loggedInUser) {
             try {
                const response = await axios.post(`http://localhost:8080/task/createTask/${table_id}`,{}, {
                    headers: {
                        Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data) {
                    window.location.reload()
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
    }
    async function deleteTask(task_id,table_id) {
        if (loggedInUser) {
            try {
                const response = await axios.delete(`http://localhost:8080/task/deleteTaskById/${task_id}/${table_id}`,{
                    headers: {
                        Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data) {
                    window.location.reload()
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
    }
    const deleteTable = async (project_id, table_id) => {
        console.log(loggedInUser.token)
        if (loggedInUser) {
             try {
                const response = await axios.delete(`http://localhost:8080/table/deleteTable/${project_id}/${table_id}`,{
                    headers: {
                        Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data) {
                    window.location.reload()
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
         }
        
    }
    const duplicateTask = async(table_id,task)=> {
        if (loggedInUser) {
            try {
                const response = await axios.post(`http://localhost:8080/task/duplicateTask/${table_id}`,JSON.stringify(task), {
                    headers: {
                        Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data) {
                    window.location.reload()
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
    } 
    const updateTableName = async (newName, tableObg) => {
        if (tableObg.table_name != newName) {  
            tableObg.table_name = newName;
            try {
                const response = await axios.put(`http://localhost:8080/table/updateTable`,JSON.stringify(tableObg), {
                    headers: {
                        Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                        'Content-Type': 'application/json'
                    }
                });
                if (response.data) {
                    console.log(response.data)
                   
                }
                } catch (error) {
                    console.error('Error fetching data:', error);
            }
        }
    } 
        
    const toogleHiddenColumns = (column) =>{
        sethiddenColumns(prevCol => ({
            ...prevCol, [column]: "block"
        }))
    }
    const loadComments = (comments,taskid) => {
        settaskComments(comments)
        toogleDropDownBoxes('commentBox',100,taskid,'commentTaskId')
    }
    const searchforMembers = async (username) => {
        if (loggedInUser && username.length() >= 4) {
            try {
            const response = await axios.post(`http://localhost:8080/users/`,{username}, {
                headers: {
                    Authorization: `Bearer ${loggedInUser.token}`, // Assuming token is stored in a variable
                    'Content-Type': 'application/json'
                }
            });
            if(response.data) {
                setmemeberFound(response.data);
            }
            } catch (error) {console.error('Error fetching data:', error);}
        }
    }
    const openAssignModel = (assignList,taskId) => {
        setassignees([])
        if (assignList.length > 0) {
            setassignees(assignList)
        }
        toogleDropDownBoxes("assigneeHieght",100,taskId,'assigneeId')
    }
   
  return (
    <>
        <div className="project-section">
              <Assign closeMode={toogleDropDownBoxes} authToken={loggedInUser?loggedInUser.token:""} taskMembers={assignees} projectPeople={oneProject.memberList} assignModel={dropDownBoxesHeight} />  
              <div className="container">
                <Comment  closeComment={toogleDropDownBoxes} dropDownValue={dropDownBoxesHeight} commentList={taskComments} />
                <h6 className='page-section-header'>{oneProject.title}</h6>
                <div className="filter_project_section">
                    <div className="page-row project_filter_row">
                        <div className="table-filters">
                            <div className="page-row">
                                <form action="" className='table_filter_col'>
                                    <i className="lni lni-search"></i>
                                    <input placeholder='Search' type="text"/>
                                </form>
                                <div className="peron_filter table_filter_col">
                                    <i className="lni lni-users"></i>
                                    <p onClick={()=>toogleDropDownBoxes("filterBox",100,0,"")}>Filter by person</p>
                                    <div style={{height:dropDownBoxesHeight.filterBox}} className="person_filter_box">
                                        <div className="filter_person_wrapper">
                                            <h6>Select member to filter with:</h6>
                                            {projectMembers.map((name,i)=>
                                            <p key={i}>{name}</p>
                                            )}
                                        </div>
                                    </div>
                                </div>
                                <div className="filter_sort table_filter_col">
                                    <i className="lni lni-sort-alpha-asc"></i>
                                    <p>Sort</p>
                                </div>
                            </div>
                        </div>
                        <div className="project_create_table_invite">
                            <div className="project_members">
                                {projectMembers.map((name,i)=>
                                   <span key={i}>{name.charAt(0)}</span>
                                )}
                            </div> 
                            <div  className="invite_btn">
                                <p onClick={()=>toogleDropDownBoxes("inviteBox", 250,0,"")}>invite <i className="lni lni-circle-plus"></i></p>
                                <div style={{height: dropDownBoxesHeight.inviteBox}} className="invite_members">
                                   <div className="member_invite_wrapper">
                                        <h5>Search members to add by name  </h5>
                                        <form action=""> <input onChange={(e)=>searchforMembers(e.target.value)} type="text" placeholder='Please search member' /><input type="submit" value="search"/></form>
                                        {memeberFound.map((memberName,i)=>
                                        <p key={i} onClick={()=>addMemberToProject(memberName)} >{memberName}</p>
                                        )}
                                        <h6>List of project members</h6>
                                        {
                                            projectMembers.map((names,index)=>
                                            <p key={index}>{names}</p>
                                            )
                                        }
                                   </div>
                                </div>
                            </div>   
                            <button onClick={()=>createTable(oneProject.project_id)}>
                                <i className="lni lni-layers"></i>
                                <span>create table</span>
                            </button>
                        </div>
                    </div>
                </div>
                {
                  oneProject.tables.map((table,table_index)=>
                        <div key={table.table_id} className="table_section">
                            <div className='edit-table'>
                                <input id='table_title' onBlur={(e)=>updateTableName(e.target.value, table)} defaultValue={table.table_name} contentEditable/>
                                <i className="lni lni-chevron-down" onClick={()=>toogleDropDownBoxes("table", 90,table_index,'tableIndex')}></i>
                                <div style={{height: table_index == dropDownBoxesHeight.tableIndex ? dropDownBoxesHeight.table :0}} className="edit-table-hidden-box">
                                    <div className='edit_table_icon' onClick={()=>deleteTable(oneProject.project_id, table.table_id)}>
                                        <i className="lni lni-trash-can"></i>
                                        <span>Delete table</span>
                                    </div>
                                    <div className='edit_table_icon'>
                                        <i className="lni lni-circle-plus"></i>
                                        <span onClick={()=>Adding_Task(table.table_id)}>Add task</span>
                                    </div>
                                </div>
                            </div>
                            <div className="project_table">
                                <div className="page-row col_name_row">
                                    <div style={{height:dropDownBoxesHeight.addColumn}}  className="add-table-column">
                                        <h6>Add extra table columns:</h6>
                                        <p onClick={()=>toogleHiddenColumns('priority')}>
                                            <i className="lni lni-sort-alpha-asc"></i>
                                            <span>Priority</span>
                                        </p>
                                    </div>
                                    <span className='field_name col_name'>Title</span>
                                    <span className='field_name col_name col-description'>Description</span>
                                    <span className='field_name col_name'>Owner</span>
                                    <span className='field_name col_name'>Assignee</span>
                                    <span className='field_name col_name'>start-date</span>
                                    <span className='field_name col_name'>end-date</span>
                                    <span className='field_name col_name'>status</span>
                                    <span style={{display:hiddenColumns.priority}} className='field_name col_name'>Priority</span>
                                    <div className='more  more-col' onClick={() => toogleDropDownBoxes("addColumn", 120, table_index, 'columnIndex')}> <i className="lni lni-circle-plus"></i></div>
                                </div>
                            {
                            table.tasks.map((task,taskIndex)=>
                              <div key={task.task_id}>
                                  <div  className="page-row table_task_row">
                                    <div style={{height:dropDownBoxesHeight.rowId == task.task_id ? dropDownBoxesHeight.editRow : 0 }} className="edit_task_box">
                                        <div className="box-arrow"></div>
                                        <p onClick={() => deleteTask(task.task_id,table.table_id)}>
                                            <i className="lni lni-trash-can"></i>
                                            <span>Delete</span>
                                        </p>
                                        <p onClick={()=>duplicateTask(table.table_id,task)}>
                                            <i className="lni lni-clipboard"></i>
                                            <span>Duplicate</span>
                                        </p>
                                    </div>
                                    <div className='field_name text_task table-task task-title'>
                                        <i onClick={()=>toogleDropDownBoxes('editRow', 90, task.task_id,"rowId" )} className="more-task lni lni-more"></i> 
                                            <input onBlur={(e)=>sendEditedRow(task, e.target.value,'')}  onChange={(e) => rowUpdate('title', e.target.
                                                value, task,table.table_id)} defaultValue={task.title} />
                                    </div>
                                    <div className='field_name table-task text_task row_description'>
                                        <input defaultValue={task.description} onBlur={(e)=>sendEditedRow(task, e.target.value,'')} onChange={(e) => rowUpdate('description', e.target.
                                            value, task, table.table_id)} />
                                            <i onClick={()=>loadComments(task.comments, task.task_id)} className="lni lni-comments-alt-2"></i>
                                    </div>
                                    <div className='field_name table-task text_task'>{task.owner}</div>
                                    <div className='assignee field_name table-task text_task'>
                                            {task.assignees.length > 0 ? 
                                                task.assignees.map((taskAssignee,i) => 
                                                    <span key={i} className='task_assign_letter' >{taskAssignee.username.charAt(0)}</span>
                                                ) : <span>invite</span>}    
                                            <i className="lni lni-circle-plus" onClick={()=>openAssignModel(task.assignees,task.task_id)}></i>
                                    </div>
                                        <div className='field_name table-task'><input onBlur={(e) => sendEditedRow(task, e.target.value, '')}
                                            onChange={(e) => rowUpdate('start_date', e.target.
                                                value, task, table.table_id)}  defaultValue={task.start_date} type="date" /></div>
                                    <div  className='field_name table-task'><input defaultValue={task.end_date} type="date" onBlur={(e)=>sendEditedRow(task, e.target.value,'')} onChange={(e)=>rowUpdate('end_date',e.target.value,task,table.table_id)} /></div>
                                    <div onClick={()=>toogleDropDownBoxes("statusBox", 160, task.task_id,'statusIndex')} id={task.status} className='status field_name table-task'>
                                        {task.status}
                                        <div style={{height:dropDownBoxesHeight.statusIndex == task.task_id ? dropDownBoxesHeight.statusBox : 0}} className="status_dropdown">
                                            <div className="status_dropdown_content">
                                                <span onClick={(e)=>sendEditedRow(task, e.target.innerText,'status')} id='Done'>Done</span>
                                                <span onClick={(e)=>sendEditedRow(task, e.target.innerText,'status')} id='ToDo'>ToDo</span>
                                                <span onClick={(e)=>sendEditedRow(task, e.target.innerText,'status')} id='InProgress'>InProgress</span>
                                            </div>
                                        </div>
                                        </div>
                                    <div style={{display:hiddenColumns.priority}} className='field_name table-task text_task'>{task.priority}</div>
                                    <div className='more'></div>
                                </div>       
                              </div>                     
                            )
                        }
                    </div>
                </div>
                  ) 
                }
            </div>
        </div>
    </>
  )
}

export default ProjectSection