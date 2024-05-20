import React, { useEffect } from "react";
import axios from "axios";
import Cookies from "js-cookie";

const OAuth2Login = () => {
  useEffect(() => {
    const handleOAuth2LoginResponse = async () => {
      try {
        // Make a GET request to the backend to complete the login process
        console.log("begin social Login fetch");
        const response = await axios.get(
          "http://localhost:8080/oauth/socialLogin",
          { withCredentials: true }
        );

        if (response.data.status == "OK") {
          console.log("end fetch");
          // Store user details in session storage
          console.log(response.data);
          // Set session storage item for system user using user details
          const userDetails = {
            email: response.data.email,
            fullName: response.data.fullname,
          };
          console.log("userDetails: " + userDetails);
          sessionStorage.setItem("systemUser", JSON.stringify(userDetails));

          // Set cookies for JWT token and refresh token
          console.log("set cookie jwtToken:" + response.data.token);
          Cookies.set("jwtToken", response.data.token, {
            path: "/",
            expires: 365,
          });
          console.log("set cookie refreshToken:" + response.data.refreshToken);
          //   console.log("successful response redirect");
          Cookies.set("refreshToken", response.data.refreshToken, {
            path: "/",
            expires: 365,
          });

          console.log(Cookies);
          console.log("cookies set successful response redirect");
          // Redirect to the desired page within your React app
          window.location.href = "/help";
        } else {
          console.log("unsuccessful response");
          console.log("Error during login:", response.data);
          console.log("Error during login:", response.data.message);
          // Handle error scenario
          window.location.href = "/login";
        }
      } catch (error) {
        console.log("unsuccessful OAuth2 login response");
        console.error("Error handling OAuth2 login response:", error);
        window.location.href = "/login";
      }
    };

    // Call the function to handle the OAuth2 login response
    console.log("Calling the handleOAuth2LoginResponse function");
    handleOAuth2LoginResponse();
  }, []);

  return <div>Loading...</div>; // TODO: add a loading indicator here
};

export default OAuth2Login;
