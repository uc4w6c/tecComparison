import * as Express from 'express';
import Post from '../../model/Post';

const router = Express.Router();

// topic一覧を返却
router.get('/', (req, res, next) => {
    Post.getPostsByTopicId(1, 1);

    // res.status(501).json({ message: 'Not Implemented.' });
    res.status(200).json({ message: 'OK' });
});

export default router;
