export interface RoleResponse {
  id: number;
  name: string;
}

export interface UserResponse {
  id: number;
  fullname: string;
  email: string;
  password: string;
  created_at: Date;
  updated_at: Date;
  deleted_at?: Date;
  pin: string;
  role: RoleResponse;
}
