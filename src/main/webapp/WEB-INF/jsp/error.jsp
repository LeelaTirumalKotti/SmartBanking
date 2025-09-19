<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            padding: 20px;
        }
        .error-container {
            border: 1px solid #f5c6cb;
            background-color: #f8d7da;
            padding: 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Oops! Something went wrong.</h1>
        <p>We encountered an unexpected error. Please try again later.</p>

               <h3>Error Details:</h3>
        <p><strong>Message:</strong> <%= exception.getMessage() %></p>
        <p><strong>Type:</strong> <%= exception.getClass().getName() %></p>
    </div>
</body>
</html>
