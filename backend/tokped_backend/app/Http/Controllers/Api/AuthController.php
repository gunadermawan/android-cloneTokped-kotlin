<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;

class AuthController extends Controller
{
    public function login(Request $request)
    {
        $user = User::where('email', $request->email)->first();
        if ($user) {
            return 'welcome ' . $user->name;
        }
        return 'welcome not user';
    }
}
