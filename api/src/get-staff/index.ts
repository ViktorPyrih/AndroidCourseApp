import { APIGatewayProxyHandler, APIGatewayProxyEvent } from "aws-lambda";

export const index: APIGatewayProxyEvent = async (event: APIGatewayProxyEvent) => {
    const staff = [
        {
            id: 0,
            name: 'Darya',
            salary: 100,
            cv: 'manager'
        },
        {
            id: 1,
            name: 'Vlad',
            salary: 90,
            cv: 'dev'
        }
    ]
    return {
        statusCode: 200,
        body: JSON.stringify(staff)
    }
};