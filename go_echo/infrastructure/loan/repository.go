package loan

type RepositoryImp struct {
}

func (r *RepositoryImp) FindRate() (float64, error) {
	return 0.5, nil
}
