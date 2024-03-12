import React,{useState} from 'react'

function ProjectSection() {
    const [statusValue, setStatusValue] = useState("Done");
    const [addColumn, setaddColumn] = useState(0);
    const [moreTask, setmoreTask] = useState([]);
    const [moreTaskOpacity, setMoreTaskOpacity] = useState(0);
    const [projectMembers, setProjectMembers] = useState(["Jane Doe"]);
    const [users, setUsers] = useState(["Alex Smith"]);
    const [memeberFound, setmemeberFound] = useState([]);
    const [inviteOpacity, setinviteOpacity] = useState(0);
    const [filterPersonOpacity, setfilterPersonOpacity] = useState(0);
    const [statusShown, setstatusShown] = useState(0);
    const [statusOpacity, setstatusOpacity] = useState(0);


    const [tables, settables] = useState([[
        {table_name:"Table Name"}, {tasks:[{title:"",Owner:"",Description:"",Start_date:"",End_date:""},
        {title:"task title",Owner:"Jane Doe",Description:"Item Description",Start_date:"",End_date:""}
        ]}
    ]]);
    
    const createTable=()=>{
        const newTable = tables[0];
        settables([...tables,newTable])
    }
    const toogleAddCol=()=>{
        addColumn ? setaddColumn(0): setaddColumn(1);
    }
    const toogleInvite=()=>{
        inviteOpacity ? setinviteOpacity(0): setinviteOpacity(1);
    }
    const toogleMoreTaskBtn=(tableIdex,taskIndex)=>{
        const newList = [tableIdex,taskIndex];
        setmoreTask(newList)
        moreTaskOpacity ? setMoreTaskOpacity(0):setMoreTaskOpacity(1);
    }
    const tooglePesronFilter=()=>{
        filterPersonOpacity ? setfilterPersonOpacity(0):setfilterPersonOpacity(1);
    }   
    const toogleStatus=(index)=>{
       console.log(index)
    }
    const showSearchedMember=(e)=>{
        const memberPresent = users.filter(name=>
            name.includes(e.target.value)
        )
        memberPresent.length > 0 && e.target.value !== "" ?setmemeberFound(memberPresent):setmemeberFound([]);
    }
    const addMemberToProject=(newMember)=>{
        setProjectMembers(prevItems =>[...prevItems,newMember])
    }   
  return (
    <>
        <div className="project-section">
            <div className="container">
                <h6 className='page-section-header'>School project</h6>
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
                                    <p onClick={tooglePesronFilter}>Filter by person</p>
                                    <div style={{opacity:filterPersonOpacity}} className="person_filter_box">
                                        <h6>Select member to filter with:</h6>
                                        {projectMembers.map((name,i)=>
                                           <p>{name}</p>
                                        )}
                                    </div>
                                </div>
                                <div className="filter_sort table_filter_col">
                                    <i class="lni lni-sort-alpha-asc"></i>
                                    <p>Sort</p>
                                </div>
                            </div>
                        </div>
                        <div className="project_create_table_invite">
                            <div className="project_members">
                                {projectMembers.map((name,i)=>
                                   <span>{name.charAt(0)}</span>
                                )}
                            </div> 
                            <div  className="invite_btn">
                                <p onClick={toogleInvite}>invite <i class="lni lni-circle-plus"></i></p>
                                <div style={{opacity: inviteOpacity}} className="invite_members">
                                    <h5>Search members to add by name  </h5>
                                    <form action=""> <input onChange={(e)=>showSearchedMember(e)} type="text" placeholder='Please search member' /><input type="submit" value="search"/></form>
                                    {memeberFound.map((memberName,i)=>
                                    <p onClick={()=>addMemberToProject(memberName)} >{memberName}</p>
                                    )}
                                    <h6>List of project members</h6>
                                    {
                                        projectMembers.map((names,index)=>
                                          <p>{names}</p>
                                        )
                                    }
                                </div>
                            </div>   
                            <button onClick={createTable}>
                                <i class="lni lni-layers"></i>
                                <span>create table</span>
                            </button>
                        </div>
                    </div>
                </div>
                {
                    tables.map((tableItem,tableIndex)=>
                        <div className="table_section">
                            <h6 id='table_title'>{tableItem[0].table_name}</h6>
                            <div className="project_table">
                        <div className="page-row col_name_row">
                            <div style={{opacity:addColumn}} className="add-table-column">
                                <h6>Add extra table columns:</h6>
                                <p>
                                    <i className="lni lni-comments-alt-2"></i>
                                    <span>Comment</span>
                                </p>
                                <p>
                                    <i className="lni lni-sort-alpha-asc"></i>
                                    <span>Priority</span>
                                </p>
                            </div>
                            <span className='field_name col_name'>Title</span>
                            <span className='field_name col_name'>Description</span>
                            <span className='field_name col_name'>Owner</span>
                            <span className='field_name col_name'>start-date</span>
                            <span className='field_name col_name'>end-date</span>
                            <span className='field_name col_name'>status</span>
                           <div className='more  more-col' onClick={toogleAddCol}> <i className="lni lni-more"></i></div>
                        </div>
                        {
                            tableItem[1].tasks.map((task,taskIdex)=>
                            <div className="page-row table_task_row">
                                <div className="box-arrow"></div>
                                <div className="box-arrow"></div>
                            <div style={{opacity: moreTask.length > 0 &&  moreTask[0] == tableIndex && moreTask[1] == taskIdex ? moreTaskOpacity :0}} className="edit_task_box">
                                <div className="box-arrow"></div>
                                <p>
                                    <i className="lni lni-trash-can"></i>
                                    <span>Delete</span>
                                </p>
                                 <p>
                                    <i className="lni lni-clipboard"></i>
                                    <span>Duplicate</span>
                                </p>
                                 <p>
                                   <i className="lni lni-circle-plus"></i>
                                    <span>Delete</span>
                                </p>
                            </div>
                            <span className='field_name text_task table-task task-title'><i onClick={()=>toogleMoreTaskBtn(tableIndex,taskIdex)} className="more-task lni lni-more"></i> <span>{task.title}</span> </span>
                            <span className='field_name table-task text_task'>{task.Description}</span>
                            <span className='field_name table-task text_task' >{task.Owner}</span>
                            <div className='field_name table-task'>
                                <input type="date"/>
                            </div>
                            <div className='field_name table-task'>
                                <input type="date"  />
                            </div>
                            <div onClick={()=>toogleStatus(1)} id={statusValue} className='status field_name table-task'>
                                {statusValue}
                                <div style={{opacity: statusShown == taskIdex ? statusOpacity: 0}} className="status_feilds">
                                    <span id='Done'>Done</span>
                                    <span id='ToDo'>To Do</span>
                                    <span id='InProgress'>In Progress</span>
                                </div>
                            </div>
                            <div className='more'></div>
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