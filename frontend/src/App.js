import { useEffect, useState } from 'react';
import './App.css';

const backendUrl = (process.env.REACT_APP_BACKEND_URL || '').replace(/\/+$/, '');
const appVersion = process.env.REACT_APP_VERSION || 'local';

function formatCurrency(value) {
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
  }).format(value);
}

function App() {
  const [products, setProducts] = useState([]);
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    let isMounted = true;

    async function loadData() {
      try {
        const [productsResponse, ordersResponse] = await Promise.all([
          fetch(`${backendUrl}/api/products`),
          fetch(`${backendUrl}/api/orders`),
        ]);

        if (!productsResponse.ok || !ordersResponse.ok) {
          throw new Error('The server could not load orders and products.');
        }

        const [productData, orderData] = await Promise.all([
          productsResponse.json(),
          ordersResponse.json(),
        ]);

        if (isMounted) {
          setProducts(productData);
          setOrders(orderData);
        }
      } catch (loadError) {
        if (isMounted) {
          setError(loadError.message || 'Unable to connect to the server.');
        }
      } finally {
        if (isMounted) {
          setLoading(false);
        }
      }
    }

    loadData();

    return () => {
      isMounted = false;
    };
  }, []);

  return (
    <div className="app-shell">
      <header className="topbar">
        <a className="brand" href="/" aria-label="Commerce home">
          <span className="brand-mark" aria-hidden="true">C</span>
          <span>Commerce</span>
        </a>
        <span className="version-badge">Version {appVersion}</span>
      </header>

      <main className="dashboard">
        <section className="welcome">
          <p className="eyebrow">STORE OVERVIEW</p>
          <h1>Orders &amp; products</h1>
          <p className="welcome-copy">
            A quick look at what customers are ordering and what is in the catalog.
          </p>
        </section>

        {loading && <p className="status-message" role="status">Loading store data…</p>}
        {error && <p className="error-message" role="alert">{error}</p>}

        {!loading && !error && (
          <div className="content-grid">
            <section className="panel" aria-labelledby="orders-heading">
              <div className="panel-heading">
                <div>
                  <p className="eyebrow">RECENT ACTIVITY</p>
                  <h2 id="orders-heading">Orders</h2>
                </div>
                <span className="count-badge">{orders.length}</span>
              </div>
              {orders.length === 0 ? (
                <p className="empty-state">No orders to show yet.</p>
              ) : (
                <div className="order-list">
                  {orders.map((order) => (
                    <article className="order-card" key={order.id}>
                      <div className="order-icon" aria-hidden="true">↗</div>
                      <div className="order-details">
                        <h3>Order #{order.id}</h3>
                        <p>{order.customerName}</p>
                        <span>
                          {order.productIds.length} {order.productIds.length === 1 ? 'item' : 'items'}
                        </span>
                      </div>
                      <strong className="order-total">{formatCurrency(order.total)}</strong>
                    </article>
                  ))}
                </div>
              )}
            </section>

            <section className="panel" aria-labelledby="products-heading">
              <div className="panel-heading">
                <div>
                  <p className="eyebrow">IN THE CATALOG</p>
                  <h2 id="products-heading">Products</h2>
                </div>
                <span className="count-badge">{products.length}</span>
              </div>
              {products.length === 0 ? (
                <p className="empty-state">No products to show yet.</p>
              ) : (
                <div className="product-list">
                  {products.map((product) => (
                    <article className="product-card" key={product.id}>
                      <div className="product-icon" aria-hidden="true">◇</div>
                      <h3>{product.name}</h3>
                      <strong>{formatCurrency(product.price)}</strong>
                    </article>
                  ))}
                </div>
              )}
            </section>
          </div>
        )}
      </main>
    </div>
  );
}

export default App;
