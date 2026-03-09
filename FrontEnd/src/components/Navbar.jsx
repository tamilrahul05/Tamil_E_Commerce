import React, { useState, useEffect } from 'react';
import { Link, NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { cartAPI } from '../services/api';

const Navbar = () => {
  const { user, logout, isAuthenticated, isAdmin } = useAuth();
  const navigate = useNavigate();
  const [cartCount, setCartCount] = useState(0);
  const [menuOpen, setMenuOpen] = useState(false);

  // Fetch cart count when user is logged in
  useEffect(() => {
    if (!isAuthenticated) { setCartCount(0); return; }
    cartAPI.get()
      .then((res) => {
        const count = res.data.items?.reduce((s, i) => s + i.quantity, 0) || 0;
        setCartCount(count);
      })
      .catch(() => {});
  }, [isAuthenticated]);

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  const initials = user?.username
    ? user.username.slice(0, 2).toUpperCase()
    : '?';

  return (
    <nav className="navbar">
      <div className="container navbar-inner">
        {/* Brand */}
        <Link to="/" className="navbar-brand">Tamil_E_Commerce</Link>

        {/* Desktop Links */}
        <div className="navbar-links">
          <NavLink to="/" className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`} end>
            Home
          </NavLink>
          <NavLink to="/products" className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`}>
            Shop
          </NavLink>
          {isAdmin && (
            <NavLink to="/admin" className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`}>
              Admin
            </NavLink>
          )}

          {isAuthenticated ? (
            <>
              {/* Cart */}
              <Link to="/cart" className="nav-cart-btn">
                <i className="fa-solid fa-bag-shopping" />
                Cart
                {cartCount > 0 && <span className="cart-badge">{cartCount}</span>}
              </Link>

              {/* User Menu */}
              <div style={{ position: 'relative' }}>
                <button
                  className="navbar-user btn btn-ghost btn-sm"
                  onClick={() => setMenuOpen(!menuOpen)}
                  style={{ gap: 10 }}
                >
                  <div className="user-avatar">{initials}</div>
                  <span style={{ fontFamily: 'var(--font-main)', fontWeight: 600 }}>
                    {user.username}
                  </span>
                  <i className={`fa-solid fa-chevron-${menuOpen ? 'up' : 'down'}`} style={{ fontSize: '0.7rem' }} />
                </button>

                {menuOpen && (
                  <div style={{
                    position: 'absolute', top: 'calc(100% + 8px)', right: 0,
                    background: 'var(--bg-card)', border: '1px solid var(--border-subtle)',
                    borderRadius: 'var(--radius-md)', padding: '8px',
                    minWidth: 160, boxShadow: 'var(--shadow-md)', zIndex: 50,
                  }}>
                    <Link
                      to="/orders"
                      className="btn btn-ghost btn-sm"
                      style={{ width: '100%', justifyContent: 'flex-start', gap: 10 }}
                      onClick={() => setMenuOpen(false)}
                    >
                      <i className="fa-solid fa-box" /> My Orders
                    </Link>
                    <button
                      className="btn btn-danger btn-sm"
                      style={{ width: '100%', justifyContent: 'flex-start', gap: 10, marginTop: 4 }}
                      onClick={handleLogout}
                    >
                      <i className="fa-solid fa-right-from-bracket" /> Logout
                    </button>
                  </div>
                )}
              </div>
            </>
          ) : (
            <>
              <Link to="/login" className="btn btn-outline btn-sm">Login</Link>
              <Link to="/register" className="btn btn-primary btn-sm">Sign Up</Link>
            </>
          )}
        </div>
      </div>

      {/* Overlay to close menu */}
      {menuOpen && (
        <div
          style={{ position: 'fixed', inset: 0, zIndex: 40 }}
          onClick={() => setMenuOpen(false)}
        />
      )}
    </nav>
  );
};

export default Navbar;
