import React, { useState } from 'react'
import axios from 'axios';

function Comment(props) {
  const [createComment, setcreateComment] = useState();
  
  const comment = async (e) => {
    e.preventDefault()
   
    const userSytem = (JSON.parse(sessionStorage.getItem("systemUser")));
    if(createComment && userSytem) {
      try {
        const response = await axios.post(`http://localhost:8080/comment/create/${props.dropDownValue.commentTaskId}`,JSON.stringify(createComment), {
            headers: {
                Authorization: `Bearer ${userSytem.token}`, // Assuming token is stored in a variable
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
  

  return (
    <>
        <div style={{height: props.dropDownValue.commentBox +'%'}} className="comment-page">
              <div className="comment_wrapper">
                    <i onClick={()=>props.closeComment('commentBox',0,0,'')}  className="lni lni-close"></i>
                    <h6 id="comment_section_title">Comments on the front end project </h6>
                    <div className="comment-section">
                        <form action="">
                            <textarea onChange={(e)=>setcreateComment({commentBody:e.target.value})} placeholder='Please type your comment here' name="" id="" cols="30" rows="7"></textarea>
                            <div className="comment-buttons">
                                <div className="letter_buttons">
                                    <span>B</span>
                                    <span>l</span>
                                    <span>U</span>
                                </div>
                                <button onClick={(e)=>comment(e)}><i className="lni lni-comments-alt-2"></i> Comment</button>
                            </div>
                        </form>
                        <div className="user_comment_thread">
                          <h6 className='comments_size'>{props.commentList.length} Comments</h6>
                          {props.commentList.map((comments,key) =>
                            <div key={key} className="comment_details">
                                <div><span id="user_letter">{comments.username.charAt(0)}</span></div>
                                <div className="user_massage">
                                      <span id="user_name">{comments.username}</span>
                                      <span id='comment_time'>{comments.date_created}</span>
                                    <p>{comments.commentBody}</p>
                                  <div className='commentEdit'>
                                    <div className='commentRemove'>
                                      <i className="lni lni-trash-can"></i>
                                       <span>Delete</span>
                                    </div>
                                   <div className='commentReplay'>
                                      <i className="lni lni-comments-reply"></i>
                                       <span>Reply</span>
                                    </div>
                                  </div>
                                    
                                </div>
                            </div>
                          )}
                        </div>
                    </div>
                </div>
        </div>
    </>
  )
}

export default Comment