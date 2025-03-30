<?php

namespace service;

interface AccessInterface
{
    public function curlApiToJSON(string $end);
}