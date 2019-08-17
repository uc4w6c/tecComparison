import * as mysql from 'mysql';

export interface DbConfig {
    host: string;
    user: string;
    password: string;
    database: string;
}
const dbConfig: DbConfig = {
    host: 'HOST_NAME',
    user:'USER_NAME',
    password:'PW',
    database:'DB'
}; 

const pool = mysql.createPool(dbConfig);
