<html>
<head>
    <title>Users</title>
</head>
<body>
    <#list users>
        <ul>
            <#items as user>
                <li>${user.name} ${user.surname} ${user.email}</li>
            </#items>
        </ul>
    <#else>
        <p>No users yet</p>
    </#list>
</body>
</html>
