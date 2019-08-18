import { Request } from "express"

export default interface PostFrom extends Request {
  id: string
}
