import React,{useEffect,useState} from 'react'
import { Outlet, Navigate, useHref } from "react-router-dom";
import { refreshToken } from "./RefreshToken";

function LoginRoute() {
    const [user, setUser] = useState();
  
    useEffect(() => {
        const fetchData = async () => {
        try {
            setUser(await refreshToken())
        } catch (error) {
        console.error('Error fetching data:', error)
        }
        }
        fetchData();
        return () => {}
    }, [user]);
    
    if(!user) {
        return <p>loading...</p>;
    }
        
  return(
    user ? <Navigate to={"/help"} /> :<Navigate to={"/"} />
  );

  
}

export default LoginRoute