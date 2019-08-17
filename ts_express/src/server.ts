import * as Express from 'express';
import topic from './routes/topic';

const app = Express();

app.get(
    '/',
    (req: Express.Request, res: Express.Response) => {
        return res.send('Hello world.');
    });

app.use('/topic', topic);

app.listen(
    3000,
    () => {
        console.log('Example app listening on port 3000!');
    });

export default app;
