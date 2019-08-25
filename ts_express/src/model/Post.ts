import DBStore from '../documents/DBStore';

export default class Post {
    private id: number;
    private topicId: number;
    private name: string;
    private body: string;
    private deletedReason: string;
    private deletedAt: string;
    private createdAt: string;
    private updatedAt: string;

    private static DISPLAYABLE_SIZE: number = 100;
    constructor(id: number,
                topicId: number,
                name: string,
                body: string,
                deletedReason: string,
                deletedAt: string,
                createdAt: string,
                updatedAt: string) {

        this.id = id;
        this.topicId = topicId;
        this.name = name;
        this.body = body;
        this.deletedReason = deletedReason;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public getId(): number {
        return this.id;
    }
    public getTopicId(): number {
        return this.topicId;
    }
    public getName(): string {
        return this.name;
    }
    public getBody(): string {
        return this.body;
    }
    public getDeletedReason(): string {
        return this.deletedReason;
    }
    public getDeletedAt(): string {
        return this.deletedAt;
    }
    public getCreatedAt(): string {
        return this.createdAt;
    }
    public getUpdatedAt(): string {
        return this.updatedAt;
    }

    public static getPostsByTopicId(id: number, page: number): Post[] {
        const posts: Post[] = [];
        try {
            const conn = DBStore.createConnection();
            // プレースホルダーは使えないらしい・・・
            // const sql = 'SELECT * from posts WHERE topic_id = :id LIMIT :offset, :rowCount';
            const sql = 'SELECT * from posts WHERE topic_id = ? LIMIT ?, ?';
            /*
            この書き方はダメっぽい。where条件になっちゃう
            const param: any = {
                id: (id),
                offset: (page - 1) * this.DISPLAYABLE_SIZE,
                rowCount: this.DISPLAYABLE_SIZE,
            };
            */
            const offset = Number((page - 1) * this.DISPLAYABLE_SIZE);
            const rowCount = Number(this.DISPLAYABLE_SIZE);

            // const data = conn.query(sql, param, (err, rows, fields) => {
            const query = conn.query(sql, [id, offset, rowCount], (err, rows, fields) => {
                if (err) { console.log('エラー・・・'); }
                console.log(rows[0]);
                /*
                forEachだとうまくいかない・・・なぜ
                rows.forEach((row) => {
                    // 途中改行の方法がわからないから一旦これで・・・
                    const post = new Post(row.id, row.topic_id, row.name, row.body, row.deleted_reason, row.deleted_at, row.created_at, row.updated_at);
                    posts.push(post);
                });
                */
            });
            console.log(query.sql);
            return posts;
        } catch (err) {
            console.log('エラーだけど・・・');
            return posts;
        }
    }
}
