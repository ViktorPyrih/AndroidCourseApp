import { APIGatewayProxyHandler, APIGatewayProxyEvent } from "aws-lambda";

export const index: APIGatewayProxyEvent = async (event: APIGatewayProxyEvent) => {
    const products = [
        {
            id: 0,
            name: 'table',
            price: 100,
            quantity: 3,
            description: 'big dinner table'
        },
        {
            id: 1,
            name: 'chair',
            price: 50,
            quantity: 5,
            description: 'chair for dinner table'
        }
    ]
    return {
        statusCode: 200,
        body: JSON.stringify(products)
    }
};