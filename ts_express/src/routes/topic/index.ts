// import * as Express from 'express';
import { Router, Response, NextFunction } from 'express';
import PostForm from '../../form/PostForm';
import Post from '../../model/Post';

// const router = Express.Router();
const router = Router();

// topic一覧を返却
// router.get('/:id', (req, res: Response, next) => {
router.get('/:id', (req: PostForm, res: Response, next: NextFunction) => {
    console.log(req.params.id)
    console.log(req.id);
    const posts = Post.getPostsByTopicId(Number(req.id), Number(req.query.page));

    // res.status(501).json({ message: 'Not Implemented.' });
    // res.status(200).json({ message: 'OK' });
    res.status(200).json(posts);
});

export default router;
