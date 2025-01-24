import * as bcrypt from 'bcrypt';

export const generateHashPassword = async (password: string) => {
  const salt = await bcrypt.genSalt();

  // generate hash password
  const hash = await bcrypt.hash(password, salt);

  return hash;
};

export const validatePassword = async (
  password: string,
  userPassword: string,
) => {
  return await bcrypt.compare(password, userPassword);
};
