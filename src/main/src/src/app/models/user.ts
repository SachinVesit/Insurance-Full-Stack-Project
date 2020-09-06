import { UserRole } from './UserRole';

export interface User {
    id: String,
    username: String,
    fullName: String,
    password: String,
    emailId: String,
    department: String,
    employeeCode: String,
    authorities: Array<String>
}
