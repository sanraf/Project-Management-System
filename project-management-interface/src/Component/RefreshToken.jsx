import axios from 'axios';

export const refreshToken = async () => {
     try {
        const response = await axios.get(`http://localhost:8080/user/refreshToken`, {
          withCredentials:true
        });
        if(response.data.jwtToken == 'jwt token not expired') {
            return true;
        }else if(response.data.jwtToken !== 'jwt token not expired') {
            Cookies.set('jwtToken', response.data.jwtToken, {
                path: '/',
            });
            Cookies.set('refreshToken', response.data.refreshToken,{
                path: '/',
            });
            return true;
        }
        } catch (error) {
          console.error('Error fetching data:', error);
          window.location.href ="/login"
      }
} 