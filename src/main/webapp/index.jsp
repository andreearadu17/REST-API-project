<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Online Tests">
    <style>
        body {
            background-image: url('${pageContext.request.contextPath}/bkg.jpg');
            background-size: cover; /* Cover the entire page */
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed; /* Keeps background static while scrolling */
            color: white; /* Ensures text is visible */
            text-align: center;
            font-family: Arial, sans-serif;
        }

        h1 {
            margin-top: 35%;
            font-size: 2.5rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        p {
            font-size: 1.2rem;
            margin-top: 20px;
        }

        a {
            color: #f8f9fa;
            text-decoration: none;
            padding: 10px 20px;
            background-color: rgba(0, 123, 255, 0.7);
            border-radius: 5px;
            margin: 0 10px;
            transition: background 0.3s;
        }

        a:hover {
            background-color: rgba(0, 123, 255, 1);
        }
    </style>

    <h1>Welcome to Online Tests</h1>
    <p>
        <a href="register.jsp">Sign Up</a> | 
        <a href="login.jsp">Login</a>
    </p>
</t:pageTemplate>
