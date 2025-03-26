<?php

namespace domain;
class User
{
    protected $login;
    protected $password;
    protected $name;
    protected $role;

    public function __construct($login, $password, $name, $role)
    {
        $this->login = $login;
        $this->password = $password;
        $this->name = $name;
        $this->role = $role;
    }

    public function getLogin()
    {
        return $this->login;
    }
}