package loan

type Repository interface {
	FindRate() (float64, error)
}
