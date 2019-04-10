<#--/*
    - content     - java.lang.String (FTL template)
    - year        - java.lang.String
    - firstName   - java.land.String
*/-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;"
    /
    <meta name="viewport" content="width=600,initial-scale = 2.3,user-scalable=no">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title></title>

    <style type="text/css">
        body {
            width: 100%;
            height: 100%;
            background-color: #fff;
            margin: 0;
            padding: 0;
            font-size: 16px;
            line-height: 1.2em;
            color: #003145;
        }

        table {
            font-size: 16px;
            border: 0;
        }

        @media only screen and (max-width: 640px) {
            .table-container {
                width: 440px !important;
            }
        }

        @media only screen and (max-width: 479px) {
            .table-container {
                width: 280px !important;
            }
        }
    </style>

</head>

<body>
<table
        border="0"
        width="100%"
        cellpadding="30"
        cellspacing="0"
        bgcolor="#f3f4f5">
    <tr>
        <td align="center"
            style="padding-top: 60px; padding-bottom: 0"
        ">
        <table
                bgcolor="#ffffff"
                align="center"
                border="0"
                width="600"
                cellpadding="50"
                cellspacing="0"
                class="table-container">
            <tbody style="width: 100%; color:#003145;">

            <tr>
                <td align="center"
                    width="100%">
                    <table
                            align="center"
                            border="0"
                            cellpadding="0"
                            cellspacing="0"
                            class="table-container">
                        <tr>
                            <td>
                                <h1 style="font-weight: bold; margin: 0 0 30px">
                                    Hi <span style="text-transform: capitalize;">${firstName}.</span>
                                    Your password ${password}
                                </h1>
                            </td>
                        </tr>

                    </table>
                </td>
            </tr>
            </tbody>
        </table>
        </td>
    </tr>

    <tr>
        <table
                align="center"
                border="0"
                width="600"
                cellpadding="0"
                cellspacing="0"
                class="table-container">
            <tr align="center">
                <td style="color: #003145; font-size: 12px; padding-top: 7.5em">
                    <span>${year} © Travel Agency</span>
                </td>
            </tr>
        </table>
    </tr>
</table>
</body>
</html>