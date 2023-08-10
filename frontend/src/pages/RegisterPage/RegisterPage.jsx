import {
  Button,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import "./RegisterPage.css";
import { useState } from "react";
import { toast } from "react-toastify";
import { axiosClient } from "src/axios/AxiosClient";

export const RegisterPage = () => {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const handleDataChange = (key, value) => {
    setFormData({ ...formData, [key]: value });
  };

  const hanndleRegister = async () => {
    const { password, confirmPassword } = formData;
    if (password !== confirmPassword) {
      toast.error("Confirm password does not match");
      return;
    }
    const { err } = await axiosClient.post("/accounts", formData);
    if (err) {
      toast.error("Register fail!");
    } else {
      toast.success("Register success!");
    }
  };
  return (
    <main className="register-page">
      <Container>
        <Row>
          <Col lg="6" className="register-page__left">
            <h3>New to our website?</h3>
            <div>
              There are advances being made in science and technology everyday,
              and a good example of this is the Create an Account
            </div>
            <button>Create an Account</button>
          </Col>
          <Col lg="6" className="register-page__right">
            <h3>LOG IN TO ENTER</h3>
            <Form>
              <FormGroup>
                <Label htmlFor="Username">Username</Label>
                <Input
                  id="username"
                  placeholder="Username"
                  onChange={(e) => {
                    handleDataChange("username", e.target.value);
                  }}
                />
              </FormGroup>
              <FormGroup>
                <Label htmlFor="email">Email Address</Label>
                <Input
                  id="email"
                  type="email"
                  placeholder="Email Address"
                  onChange={(e) => {
                    handleDataChange("email", e.target.value);
                  }}
                />
              </FormGroup>
              <FormGroup>
                <Label for="password">Password</Label>
                <Input
                  type="password"
                  id="password"
                  placeholder="Password"
                  onChange={(e) => {
                    handleDataChange("password", e.target.value);
                  }}
                />
              </FormGroup>
              <FormGroup>
                <Label for="confirmPassword">Confirm Password</Label>
                <Input
                  type="password"
                  id="confirmPassword"
                  placeholder="Confirm Password"
                  onChange={(e) => {
                    handleDataChange("confirmPassword", e.target.value);
                  }}
                />
              </FormGroup>
              <Button outline color="primary" onClick={hanndleRegister}>
                Register
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </main>
  );
};
