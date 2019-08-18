import * as mysql from 'mysql';

/**
 * DBコネクションプール作成クラス
 * これであっているかわからん
 */
export default class DBStore {
    private static connection: mysql.Connection;

    // データベースの設定
    private static connectionOptions = {
        host: '127.0.0.1',
        port: 3307,
        user: 'test',
        password: 'test',
        database: 'testdb',
    };

    // コネクションプール作成
    public static createConnection() {
        if (!this.connection) {
            this.connection = mysql.createConnection(this.connectionOptions);
        }
        return this.connection;
    }
}
