import * as Express from 'express';

const router = Express.Router();

// topic一覧を返却
router.get('/', (req, res, next) => {
    // res.status(501).json({ message: 'Not Implemented.' });
    res.status(200).json({ message: 'OK' });
});

export default router;
