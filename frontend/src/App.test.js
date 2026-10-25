import { render, screen, waitFor } from '@testing-library/react';
import App from './App';

beforeEach(() => {
  global.fetch = jest.fn((url) => Promise.resolve({
    ok: true,
    json: () => Promise.resolve(
      url.endsWith('/api/products')
        ? [{ id: 1, name: 'Wireless Mouse', price: 24.99 }]
        : [{ id: 1, customerName: 'Alex Morgan', productIds: [1, 2], total: 104.98 }]
    ),
  }));
});

afterEach(() => {
  jest.restoreAllMocks();
});

test('loads orders and products from the backend and displays the app version', async () => {
  render(<App />);

  expect(screen.getByText(/loading store data/i)).toBeInTheDocument();

  await waitFor(() => {
    expect(screen.getByRole('heading', { name: 'Orders' })).toBeInTheDocument();
  });

  expect(screen.getByText('Wireless Mouse')).toBeInTheDocument();
  expect(screen.getByText('Alex Morgan')).toBeInTheDocument();
  expect(screen.getByText(/version/i)).toBeInTheDocument();
  expect(global.fetch).toHaveBeenCalledTimes(2);
});

test('shows an error when the backend cannot be reached', async () => {
  global.fetch.mockImplementation(() => Promise.reject(new Error('Network unavailable')));

  render(<App />);

  expect(await screen.findByRole('alert')).toHaveTextContent('Network unavailable');
});
