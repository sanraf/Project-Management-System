import { Outlet, Navigate, useHref } from "react-router-dom";
import React,{useEffect,useState} from "react";
import { useAuth } from "./AuthProvider";
import axios from 'axios';
import { refreshToken } from "./RefreshToken";


const PrivateRoutes = () => {
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
    user ? <Outlet/>:<Navigate to={"/"} />
  );
}
export default PrivateRoutes;