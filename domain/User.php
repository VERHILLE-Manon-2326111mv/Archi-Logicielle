<?php

namespace domain;
class User
{
    protected $id_user;
    protected $password;
    protected $name;
    protected $role;

    public function __construct($id_user, $password, $name, $role)
    {
        $this->id_user = $id_user;
        $this->password = $password;
        $this->name = $name;
        $this->role = $role;
    }

    public function getName()
    {
        return $this->name;
    }
}